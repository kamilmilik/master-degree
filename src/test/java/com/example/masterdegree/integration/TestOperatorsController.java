//package com.example.masterdegree.integration;
//
//import com.example.masterdegree.models.entity.Channel;
//import com.example.masterdegree.models.entity.Operator;
//import com.example.masterdegree.models.entity.MainTvPackage;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.bson.types.ObjectId;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
////@WebMvcTest(controllers = OperatorsController.class)
//
////@ContextConfiguration(classes= Application.class)
//public class TestOperatorsController {
//
//    Channel eurosport1 = new Channel("Eurosport1", "Kanal euro sport", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Eurosport_Logo.svg/1200px-Eurosport_Logo.svg.png");
//    Channel nsport = new Channel("nsport", "Kanal n sport", "http://ocdn.eu/images/program-tv/OWI7MDA_/518e09a968bd9238a72d45e37e9c8ac4.png");
//    List<Channel> channelList = new ArrayList<>();
//    MainTvPackage tvPackageComfort = new MainTvPackage("Comfort +","", 39.99, "main", "","https://sklep.pl.canalplus.com/oferta/comfortplus-ns", "24 miesiace", "0", channelList, null);
//    List<MainTvPackage> tvPackageList = new ArrayList<>();
//    Operator operator = new Operator(ObjectId.get(), "Test Canal+", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Platforma_Canal%2B.svg/1200px-Platforma_Canal%2B.svg.png", tvPackageList);
//    @Autowired
//    private MockMvc mvc;
//
//    @Before
//    public void setup() {
//        channelList.add(eurosport1);
//        channelList.add(nsport);
//        tvPackageList.add(tvPackageComfort);
//    }
//
//    @Test
//    public void testFetchOperator() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .post("/api/operators")
//                .content(asJsonString(operator))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        )
//                .andExpect(status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id", hasSize(1)))
//                .andReturn();
//
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
