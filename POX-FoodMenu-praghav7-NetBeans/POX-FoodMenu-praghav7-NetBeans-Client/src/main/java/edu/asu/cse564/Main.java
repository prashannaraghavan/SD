
public class Main {

    public static void main(String[] args) {
        RESTClient client= new RESTClient();

        String requestMessage = "<NewFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">     <FoodItem country=\"GB\">         <name>Cornish Pasty 1</name>         <description>Tender cubes of steak, potatoes and swede wrapped in flakey short crust pastry.  Seasoned with lots of pepper.  Served with mashed potatoes, peas and a side of gravy</description>         <category>Dinner</category>         <price>15.95</price>     </FoodItem> </NewFoodItems >";
        
        String response = client.post(requestMessage);

        
            System.out.println(response);
        
        
    }
}
