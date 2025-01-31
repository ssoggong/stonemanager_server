package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.user.AuthRequest;
import com.ssoggong.stonemanager_server.dto.user.EmailAuthRequest;
import com.ssoggong.stonemanager_server.entity.Auth;
import com.ssoggong.stonemanager_server.exception.MultipleNotFoundException;
import com.ssoggong.stonemanager_server.exception.WrongAuthcodeException;
import com.ssoggong.stonemanager_server.repository.AuthRepository;
import com.ssoggong.stonemanager_server.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private JavaMailSender mailSender;

    private final AuthRepository authRepository;

    @Transactional
    public void saveAuth(Auth auth){ authRepository.save(auth); }

    @Transactional
    public void sendAuthcode(EmailAuthRequest request){
        SimpleMailMessage message = new SimpleMailMessage();
        String code = createRandomCode();
        message.setTo(request.getEmail());
        message.setFrom(Constants.FROM_ADDRESS);
        message.setSubject(Constants.MAIL_TITLE);
        message.setText(Constants.MAIL_CONTENT + code);
        mailSender.send(message);
        Auth auth = Auth.builder().email(request.getEmail()).code(code).build();
        saveAuth(auth);
    }

    public String createRandomCode(){
        String random = String.valueOf(Math.random()*10000).substring(0,4);
        return random;
    }

    @Transactional
    public void checkAuthcode(AuthRequest request){
        Auth auth = authRepository.findByEmail(request.getEmail()).orElseThrow(MultipleNotFoundException::new);
        if(!auth.getCode().equals(request.getCode())) throw new WrongAuthcodeException(request.getCode());
        authRepository.delete(auth);
    }
}
