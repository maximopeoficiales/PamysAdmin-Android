package com.maximoprog.pamysadmin.api.services;

import com.maximoprog.pamysadmin.api.RetrofitInstance;
import com.maximoprog.pamysadmin.models.AuthBody;
import com.maximoprog.pamysadmin.models.AuthResponse;
import com.maximoprog.pamysadmin.repository.IAuthRepository;

import io.reactivex.rxjava3.core.Observable;

public class AuthService implements IAuthRepository {
    private final IAuthRepository repository;

    public AuthService() {
        repository = RetrofitInstance.getInstance().createRepository(IAuthRepository.class);
    }

    @Override
    public Observable<AuthResponse> authenticate(AuthBody auth) {
        return repository.authenticate(auth);
    }
}
