package pl.sda.sdaPostman;

import org.codehaus.jackson.map.ObjectMapper;
import pl.sda.requests.CreateUserRequest;
import pl.sda.requests.CreateUserResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> usersIds = new ArrayList<>();
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Add user");
            System.out.println("2. Show user ids list");

            String choiceRaw = scanner.nextLine();
            Integer choice = Integer.parseInt(choiceRaw);


            switch (choice){
                case 1:
                    System.out.println("Enter Your name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter Your login:");
                    String login = scanner.nextLine();

                    System.out.println("Enter Your mail:");
                    String mail = scanner.nextLine();

                    CreateUserRequest createUserRequest = new CreateUserRequest();
                    createUserRequest.setName(name);
                    createUserRequest.setLogin(login);
                    createUserRequest.setMail(mail);

                    ObjectMapper mapper = new ObjectMapper();
                    String request = mapper.writeValueAsString(createUserRequest);


//                    System.out.println(request.toString());
                    String createUserResponse = Sender.createUser("http://localhost:8081/sda-json/json", request);

                    CreateUserResponse response = mapper.readValue(createUserResponse, CreateUserResponse.class);
//                    System.out.println(response.getId());
                    usersIds.add(response.getId());

                    break;

                case 2:
                    System.out.println(usersIds.toString());
                    break;
                default: break;

            }






        }
    }
}
