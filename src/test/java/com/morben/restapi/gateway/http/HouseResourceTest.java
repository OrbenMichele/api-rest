package com.morben.restapi.gateway.http;

import com.morben.restapi.service.impl.HouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@SpringBootTest
@DisplayName("HouseRepository deveria")
public class HouseResourceTest {

    @InjectMocks
    private HouseResource inTest;

    @Mock
    private HouseServiceImpl houseService;

    @BeforeEach
    public void setUp() {
        inTest = Mockito.spy(HouseResource.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("given Method Call when GetAllAsString then Call HouseServiceRemote")
    public void givenMethodCall_whenGetAllAsString_thenCallHouseServiceRemote() {
        when(houseService.getAll()).thenReturn(null);
        inTest.getAll();
        verify(houseService, times(1)).getAll();
    }


}