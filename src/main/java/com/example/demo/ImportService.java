package com.example.demo;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.UserReviews;
import com.example.demo.repository.UserReviewsRepository;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpEntity;
import java.util.List;

@Service
@Scope("singleton")
public class ImportService {

    // This function calls the review api and returns response
    public void importReviews(String resourceUrl,UserReviewsRepository userReviewRepo){
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            //HTTP GET method
            HttpGet httpget = new HttpGet(resourceUrl);

            //Add headers to the get object
            httpget.addHeader("x-api-key", "bc9pgFjR0O5s60Ovxu0L88S5EYE3zXfN9PpqT2On");

            ResponseDTO responseDTO = getResponse(httpclient, httpget);

            //fetch the total number of pages the api returns
            int pages=responseDTO.getPaging().getTotalPages();

            //iterate through all pages to import the reviews
            for(int pageno=1;pageno<=pages;pageno++){

                //Create the review api by passing query parameter to the review api
                httpget = new HttpGet(resourceUrl+"?"+"page="+pageno);

                httpget.addHeader("x-api-key", "bc9pgFjR0O5s60Ovxu0L88S5EYE3zXfN9PpqT2On");
                responseDTO = getResponse(httpclient, httpget);

                //Iterate through each review returned in response
                List<UserReviews> reviews = responseDTO.getReviews().stream().filter(r -> !(userReviewRepo.findById(r.getId())).isPresent()).map(review -> {
                    //set reviews using the model class only if the id is not present
                    UserReviews userReview=new UserReviews.UserReviewsBuilder()
                            .id(review.getId())
                            .author(review.getAuthor())
                            .rating(review.getRating())
                            .type(review.getType())
                            .source(review.getSource())
                            .content(review.getContent())
                            .reviewDate(review.getReviewDate())
                            .tags(review.getTags())
                            .companyName(review.getCustomer().getCompanyName())
                            .build();

                    //save the entire object of user reviews
                    userReviewRepo.save(userReview);
                    return userReview;
                }).collect(Collectors.toList());

                //Save new reviews to db
                userReviewRepo.saveAll(reviews);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private ResponseDTO getResponse(CloseableHttpClient httpClient, HttpGet httpget) throws IOException {
        ResponseHandler < String > responseHandler = response -> {

            //save response status in status variable
            int status = response.getStatusLine().getStatusCode();

            //Throw Exception in case of error
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };

        //Execute the request to review api using httpclient
        String responseBody = httpClient.execute(httpget, responseHandler);

        //Using objectmapper to parse JSON content to java object
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //return the response received from api after parsing
        return objectMapper.readValue(responseBody,ResponseDTO.class);
    }
}
