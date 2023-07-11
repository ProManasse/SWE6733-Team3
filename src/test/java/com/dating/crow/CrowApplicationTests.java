package com.dating.crow;

import com.dating.crow.controller.ProfileController;
import com.dating.crow.profile.dto.ProfileDto;
import com.dating.crow.profile.model.Profile;
import com.dating.crow.profile.service.ProfileService;
import com.dating.crow.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ProfileController.class)
class CrowApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	ProfileService profileService;

	@MockBean
	UserRepository userRepository;

	@Test
	void givenProfileObject_whenCreatedProfile_thenReturnSavedProfile() throws JsonProcessingException, Exception {
		ProfileDto pd = new ProfileDto();
		pd.setName("Manzi");
		pd.setOtherName("Sabin");
		pd.setDob("01-01-1994");
		pd.setSkill("MEDICINE");
		pd.setAdventure("HIKING");
		pd.setAddress("741 Northern Ave");
		pd.setUsername("muro");
		pd.setPhoneNo("0784758478");
		pd.setBehavior("OPTIMISTIC");
		given(profileService.create(any(ProfileDto.class))).willAnswer((invocation) -> invocation.getArgument(0));

		ResultActions response = mockMvc.perform(post("/api/profile/profile").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(pd)));

		response.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(pd.getName())))
				.andExpect(jsonPath("$.otherName", is(pd.getOtherName())));

	}
	
    @Test
    public void givenListOfProfiles_whenGetAllProfiles_thenReturnProfilesList() throws Exception{
        // given - precondition or setup
        List<Profile> listOfProfiles = new ArrayList<>();
        listOfProfiles.add(new Profile());
        listOfProfiles.add(new Profile());
        listOfProfiles.add(new Profile());
        ResultActions response = mockMvc.perform(get("/api/profile/profiles"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfProfiles.size())));

    }
    
    @Test
    public void display() {
    	System.out.println("Tested");
    }
    

}
