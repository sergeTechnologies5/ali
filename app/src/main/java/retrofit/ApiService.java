package retrofit;

import java.util.List;

import models.Login;
import models.LoginResponse;
import models.Payment;
import models.PaymentResponse;
import models.Services;
import models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("createaccount.php")
    Call<PaymentResponse> createAccount(@Body User user);

    @GET("viewservices.php")
    Call<List<Services>> getServices();

    @POST("mpesa/requestcheckout.php")
    Call<PaymentResponse> payForservices(@Body Payment payment);

    @POST("login.php")
    Call<LoginResponse>  login(@Body Login login);
}
