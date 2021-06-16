package com.hjkportfolio.hjk.user;

import com.hjkportfolio.hjk.exception.InsertFailException;

import java.util.Optional;

public interface LoginControllerImpl {
    public Optional<AdminVO> login(AdminVO adminVO);
    public void insert(AdminVO adminVO) throws InsertFailException;

}
