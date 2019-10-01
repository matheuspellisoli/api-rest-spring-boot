package br.com.pellisoli.app;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.pellisoli.app.models.PersonModel;
import br.com.pellisoli.app.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PeopleControllerTest {

		
    @Autowired
    private TestRestTemplate restTemplate;  
    
    @LocalServerPort
    private int port;
    
    @MockBean
    PersonRepository personRepository;
    
    @Autowired
    private MockMvc mockMVC;
    
    @Test
    public void getAllPersons() throws Exception {
    	List<PersonModel> personList = new ArrayList<PersonModel>();
    	 
    	personList.add(new PersonModel(1, "Eliane", "Rosângela Bernardes", "7999461760", "70143815989"));
    	personList.add(new PersonModel(2, "Tânia", "Stefany Gomes", "82991213354", "41709325526"));
    	personList.add(new PersonModel(3, "Emilly", "Andreia Daiane Castro", "51997695050", "54485821250"));
    	personList.add(new PersonModel(4, "Bento", "Henrique Eduardo Aparício", "84991115728", "28187683732"));
    	personList.add(new PersonModel(5, "Gael", "José Rezende", "11995870186", "95306889620"));
    	
    	BDDMockito.when(personRepository.findAll()).thenReturn(personList);  
    	
    	ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/people", String.class);
    	
    	Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);   	
    }
    
    @Test
    public void getByIdPersonsStatusCode404() throws Exception {    	
    	BDDMockito.when(personRepository.findById(0)).thenReturn(null);  
    	
    	ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/people/0", String.class);
    	
    	Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);   	
    }
    
    @Test
    public void getByIdPersonsStatusCode200() throws Exception {    	
    	BDDMockito.when(personRepository.findById(1)).thenReturn(new PersonModel(1, "Eliane", "Rosângela Bernardes", "7999461760", "70143815989"));  
    	ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/people/1", String.class);
    	
    	Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);   	
    }
    
    @Test
    public void postPersonsStatusCode200() throws Exception{    	
    	PersonModel personModel =  new PersonModel(1, "Eliane", "Rosângela Bernardes", "7999461760", "70143815989");
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<PersonModel> request = new HttpEntity<PersonModel>(personModel, headers);
    	
    	ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/people", request, String.class);
    	
    	Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);   	
    }
    
    @Test
    public void postPersonsStatusCode422InvalidMobilePhone() throws Exception{
    	PersonModel personModel =  new PersonModel(1, "Eliane", "Rosângela Bernardes", "799946176", "70143815989");
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<PersonModel> request = new HttpEntity<PersonModel>(personModel, headers);
    	
    	ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/people", request, String.class);
    	
    	Assertions.assertThat(response.getBody()).asString().contains("Error - Invalid Mobile Phone");
    }
    
    @Test
    public void postPersonsStatusCode422InvalidCpf() throws Exception{
    	PersonModel personModel =  new PersonModel(1, "Eliane", "Rosângela Bernardes", "7999461760", "70143815953");
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<PersonModel> request = new HttpEntity<PersonModel>(personModel, headers);
    	
    	ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/people", request, String.class);
    	
    	Assertions.assertThat(response.getBody()).asString().contains("Error - Invalid CPF");
    }
    
    @Test
    public void postPersonsStatusCode422NameIsNull() throws Exception{
    	PersonModel personModel =  new PersonModel(1, "", "Rosângela Bernardes", "7999461760", "70143815989");
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<PersonModel> request = new HttpEntity<PersonModel>(personModel, headers);
    	
    	ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/people", request, String.class);

    	Assertions.assertThat(response.getBody()).asString().contains("Error - Invalid Name");
    }
}
