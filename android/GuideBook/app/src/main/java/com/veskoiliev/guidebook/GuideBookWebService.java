package com.veskoiliev.guidebook;

import com.veskoiliev.guidebook.model.GuideResponse;

import retrofit.Call;
import retrofit.http.GET;

public interface GuideBookWebService {

    @GET("/service/v2/upcomingGuides/")
    Call<GuideResponse> getGuides();
}
