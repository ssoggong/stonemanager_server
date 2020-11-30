package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.project.ReadProjectListDto;
import com.ssoggong.stonemanager_server.dto.project.ReadProjectListResponse;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawDto;
import com.ssoggong.stonemanager_server.dto.project.ProjectWithdrawResponse;
import com.ssoggong.stonemanager_server.dto.user.*;
import com.ssoggong.stonemanager_server.entity.*;
import com.ssoggong.stonemanager_server.exception.*;
import com.ssoggong.stonemanager_server.repository.UserRepository;
import com.ssoggong.stonemanager_server.repository.UserSubjectRepository;
import com.ssoggong.stonemanager_server.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private JavaMailSender mailSender;

    private final UserRepository userRepository;
    private final ProjectService projectService;
    private final UserSubjectRepository userSubjectRepository;

    @Transactional
    public void saveUser(User user) { userRepository.save(user); }

    public User findById(Long userId) { return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)); }

    public Subject findSubjectByUserAndSubject(Long userId, Long subjectId){
        User user = findById(userId);
        System.out.println(user.getUserSubjectSet().size());
        List<UserSubject> userSubjects = user.getUserSubjectSet().stream()
                .filter(userSubject -> userSubject.getSubject().getIdx().equals(subjectId))
                .collect(Collectors.toList());
        System.out.println(userSubjects.size());

        if(userSubjects.size() != 1) throw new MultipleNotFoundException();
        return userSubjects.get(0).getSubject();
    }

    public ReadProjectListResponse ReadProjectList(Long userId) {
        User user = findById(userId);
        Set<ProjectUser> projectUserSet = user.getProjectUserSet();
        List<ReadProjectListDto> dto = new ArrayList<>();
        for(ProjectUser projectUser: projectUserSet) {
            Project project = projectUser.getProject();
            dto.add(ReadProjectListDto.of(project, projectService.getProgressRate(project)));
        }
        return new ReadProjectListResponse(dto);
    }

    public ProjectWithdrawResponse getProjectForWithdraw(Long userId) {
        User user = findById(userId);
        Set<ProjectUser> projectUserSet = user.getProjectUserSet();
        List<ProjectWithdrawDto> dto = new ArrayList<>();
        for (ProjectUser projectUser : projectUserSet) {
            dto.add(ProjectWithdrawDto.of(projectUser.getProject()));
        }
        return new ProjectWithdrawResponse(dto);
    }

    public UserInfoResponse readUserInfo(Long userId){
        User user = findById(userId);
        return new UserInfoResponse(user.getName(), user.getStudentId(), user.getEmail(), user.getEmail());
    }

    public UserSubjectResponse readUserSubject(Long userId){
        User user = findById(userId);
        List<UserSubjectDto> dtos = new ArrayList<>();
        for(UserSubject userSubject : user.getUserSubjectSet()){
            if(userSubject.getUser().getIdx() == userId){
                dtos.add(UserSubjectDto.of(userSubject.getSubject()));
            }
        }
        return new UserSubjectResponse(dtos);
    }

    @Transactional
    public void updateProfileImage(Long userId, String imageUri){
        User user = findById(userId);
        user.setImage(imageUri);
        userRepository.save(user);
    }

    @Transactional
    public UserResponse createUser(RegisterUserRequest request, List<Subject> subjects){
        if(userRepository.findByStudentId(request.getStudentId()).isPresent()) throw new NotFoundException("Already Exist..");
        User user = User.builder()
                .name(request.getUserName())
                .studentId(request.getStudentId())
                .pw(request.getPassword())
                .email(request.getEmail())
                .image(null)
                .salt(null)
                .userTaskSet(new HashSet<>())
                .userSubjectSet(new HashSet<>())
                .userScheduleSet(new HashSet<>())
                .build();
        for(Subject subject : subjects){
            UserSubject userSubject = UserSubject.builder().subject(subject).user(user).build();
            userSubjectRepository.save(userSubject);
        }
        saveUser(user);
        return new UserResponse(user.getIdx());
    }

    @Transactional
    public UserResponse userLogin(LoginRequest request){
        User user = userRepository.findByStudentId(request.getUserId()).orElseThrow(() -> new UserNotFoundException(-1L));
        if(!user.getPw().equals(request.getPassword())) throw new WrongPasswordException(request.getPassword());
        return new UserResponse(user.getIdx());
    }

    @Transactional
    public void updatePassword(UpdatePasswordRequest request, Long userId){
        User user = findById(userId);
        if(!user.getPw().equals(request.getPassword())) throw new WrongPasswordException(request.getPassword());
        user.setPw(request.getNewPassword());
        saveUser(user);
    }

    @Transactional
    public void findPassword(FindPasswordRequest request, Long userId){
        User user = findById(userId);
        System.out.println(user.getEmail());
        if(!user.getName().equals(request.getUserName())) throw new WrongUsernameException(request.getUserName());
        if(!user.getStudentId().equals(request.getUserStudentId())) throw new WrongStudentIdException(request.getUserStudentId());
        if(!user.getEmail().equals(request.getUserEmail())) throw new WrongUseremailException(request.getUserEmail());
        String newPassword = createNewPassword();
        user.setPw(newPassword);
        saveUser(user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setFrom(Constants.FROM_ADDRESS);
        message.setSubject(Constants.MAIL_PW_TITLE);
        message.setText(Constants.MAIL_PW_CONTENT + newPassword);
        mailSender.send(message);
    }

    public String createNewPassword(){
        int min = 0;
        int max = 25;
        char random;
        String str = "";
        for(int i=0; i<6; i++){
            random = (char) ((Math.random() * (max - min)) + min + 97);
            str += random;
        }
        String num = String.valueOf((int)(Math.random()*10000));
        return str+num;
    }

    public void inviteMember(List<Long> members){
        for(Long userId: members){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(findById(userId).getEmail());
            message.setFrom(Constants.FROM_ADDRESS);
            message.setSubject(Constants.MAIL_INVITE_TITLE);
            // TODO 링크 뭐주지..? 화이트 라벨 화면 나올텐데..
            message.setText(Constants.MAIL_INVITE_CONTENT);
            mailSender.send(message);
        }
    }
}