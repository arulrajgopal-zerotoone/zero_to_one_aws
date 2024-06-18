package group1;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.*;
import org.json.*;

public class lambda_invoke_functions {

    public static void main(String[] args) throws InterruptedException {

        Region region = Region.US_EAST_1;
        LambdaClient awsLambda = LambdaClient.builder().region(region).build();
                
        invokeFunction(awsLambda, "myFunction");
        }
        public static void invokeFunction(LambdaClient awsLambda, String functionName) {

        try {
            // Need a SdkBytes instance for the payload.
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("key1", 100);
            jsonObj.put("key2", 50);
            String json = jsonObj.toString();

            SdkBytes payload = SdkBytes.fromUtf8String(json);
            InvokeRequest request = InvokeRequest.builder()
                    .functionName(functionName)
                    .payload(payload)
                    .build();

            InvokeResponse res = awsLambda.invoke(request);
            String value = res.payload().asUtf8String();
            System.out.println(value);

        } 
        catch (LambdaException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }



}

