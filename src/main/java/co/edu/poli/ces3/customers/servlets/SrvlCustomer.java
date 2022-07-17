package co.edu.poli.ces3.customers.servlets;

import co.edu.poli.ces3.customers.entities.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "SrvlCustomer", value = "/SrvlCustomer")
public class SrvlCustomer extends HttpServlet {
    private GsonBuilder gsonBuilder;
    private Gson gson;
    public SrvlCustomer() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public static ArrayList<Customer> CUSTOMER=new ArrayList<>(Arrays.asList(
            new Customer("1111","Hernan","Paz","3256633","hernan@gmail.com","Medellin","CR 56 # 25 - 25",
                    "Omar","312454566"),
            new Customer("356525","Martin","Diaz","23564556","Martin@gmail.com","Medellin","Cr 58 = 56-52",
                    "Vanessa","31251255"),
            new Customer("5266558","Esteban","Gomez","3254652563","Gomez@gmail.com","Medellin","Cr 58 = 56-52",
                    "Andres","3145624563")
    ));
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        this.setAccessControlHeaders(response);
        if(request.getParameter("CustomerId") == null){
            out.print(gson.toJson(this.CUSTOMER));
        }else{
            Customer cust = this.findCustomer(request.getParameter("CustomerId"));
            out.print(gson.toJson(cust));
        }
        out.flush();
    }
    private Customer findCustomer(String CustomerId){
        return this.CUSTOMER.stream()
                .filter(cust -> cust.getDocument().equals(CustomerId))
                .findAny()
                .orElse(null);
    }
    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean exits=false;
        boolean validate=true;
        this.setAccessControlHeaders(response);
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(request);
        Customer customer = new Customer(
                body.get("document").getAsString(),
                body.get("name").getAsString(),
                body.get("lastName").getAsString(),
                body.get("cellPhone").getAsString(),
                body.get("email").getAsString(),
                body.get("municipality").getAsString(),
                body.get("address").getAsString(),
                body.get("contactName").getAsString(),
                body.get("cellPhoneContact").getAsString()
        );
        //validacion datos que contagan los formatos correctos
        if(!customer.getDocument().matches("[+-]?\\d*(\\.\\d+)?")){
            validate=false;
            out.println("*Document solo acepta numeros");
        }
        if(!customer.getCellPhoneContact().matches("[+-]?\\d*(\\.\\d+)?")){
            validate=false;
            out.println("*CellPhoneContact solo acepta numeros");
        }
        if(!customer.getCellPhone().matches("[+-]?\\d*(\\.\\d+)?")){
            validate=false;
            out.println("*cellPhone solo acepta numeros");
        }
        if(!customer.getEmail().matches("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+")){
            validate=false;
            out.println("*Email contiene un formato incorrecto");
        }
        //si la validacion resulta correcta almacena la informacion
        if(validate) {
            //Validad si ya existe un cliente con ese documento
            for (Customer x : CUSTOMER) {
                if (x.getDocument().equals(customer.getDocument())) {
                    exits = true;
                }
            }
            if (!exits) {
                this.CUSTOMER.add(customer);
                out.print("Cliente guardado exitosamente");
                out.print(gson.toJson(customer));
            } else {
                out.print("Ya existe un cliente registrado con este numero de documento: " + customer.getDocument());
            }
        }

        out.flush();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.setAccessControlHeaders(response);
        boolean validate=true;
        int pos=0;
        int aux=0;
        boolean exits=false;
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(request);
        Customer customer = new Customer(
                body.get("document").getAsString(),
                body.get("name").getAsString(),
                body.get("lastName").getAsString(),
                body.get("cellPhone").getAsString(),
                body.get("email").getAsString(),
                body.get("municipality").getAsString(),
                body.get("address").getAsString(),
                body.get("contactName").getAsString(),
                body.get("cellPhoneContact").getAsString()
        );

        //validacion datos que contagan los formatos correctos
        if(!customer.getDocument().matches("[+-]?\\d*(\\.\\d+)?")){
            validate=false;
            out.println("*Document solo acepta numeros");
        }
        if(!customer.getCellPhoneContact().matches("[+-]?\\d*(\\.\\d+)?")){
            validate=false;
            out.println("*CellPhoneContact solo acepta numeros");
        }
        if(!customer.getCellPhone().matches("[+-]?\\d*(\\.\\d+)?")){
            validate=false;
            out.println("*cellPhone solo acepta numeros");
        }
        if(!customer.getEmail().matches("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+")){
            validate=false;
            out.println("*Email contiene un formato incorrecto");
        }

        //si la validacion resulta correcta almacena la informacion
        if(validate) {
            for (Customer x : CUSTOMER) {
                if (x.getDocument().equals(customer.getDocument())) {
                    aux = pos;
                    exits=true;
                }
                pos++;
            }
            if(exits){
                CUSTOMER.remove(aux);
                CUSTOMER.add(aux, customer);

                out.print("Cliente actualizado exitosamente");
                out.print(gson.toJson(customer));
            }else{
                out.print("Cliente no existe");
            }

        }
        out.flush();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.setAccessControlHeaders(response);
        int pos=0;
        int aux=0;
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(request);
        String document=body.get("document").getAsString();

        for (Customer x: CUSTOMER){
            if(x.getDocument().equals(document)){
                aux=pos;
            }
            pos++;
        }
        CUSTOMER.remove(aux);

        out.print("Cliente eliminado con numero de documento: "+gson.toJson(document));
        out.flush();
    }


    private JsonObject getParamsFromPost(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();

        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }
}
