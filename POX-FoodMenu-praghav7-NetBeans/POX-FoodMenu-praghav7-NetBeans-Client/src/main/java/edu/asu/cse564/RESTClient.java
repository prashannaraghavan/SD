
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;

public class RESTClient {

    private final WebResource webResource;
    private final Client client;
    public RESTClient() {
        ClientConfig clientConfig = new DefaultClientConfig();
        client = Client.create(clientConfig);
        webResource = client.resource("http://localhost:8080/POX-FoodMenu-praghav7-NetBeans-Server/webresources/restservices/FoodItem");
    }

    public String post(String xml) throws UniformInterfaceException {
        ClientResponse response = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, xml);
        if (response.getStatus() == 200) {
            return response.getEntity(String.class);
        }else{
            return "error :"+response.getStatus();
	}
    }

    public void close() {
        client.destroy();
    }
}
