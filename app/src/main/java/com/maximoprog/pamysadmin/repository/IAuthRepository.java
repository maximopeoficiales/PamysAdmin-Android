package com.maximoprog.pamysadmin.repository;

import com.maximoprog.pamysadmin.enviroments.Credentials;
import com.maximoprog.pamysadmin.models.AuthBody;
import com.maximoprog.pamysadmin.models.AuthResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthRepository {
    @POST(Credentials.URI_AUTH)
    Observable<AuthResponse> authenticate(@Body AuthBody auth);
}
