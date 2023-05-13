package com.m2i.filrougebo.service;
import com.m2i.filrougebo.dao.IntAdminDao;
import com.m2i.filrougebo.dto.AdminDto;
import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.entity.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    @Mock
    private IntAdminDao adminDaoMock;
    @InjectMocks
    private AdminService adminService;
    @Test
    void ShouldReturnAllAdmins(){

        List<Admin> admins = new ArrayList<>();
        Admin admin1 = new Admin("admin1","password1");
        admin1.setIdAmin(1);
        Admin admin2 = new Admin("admin2","password2");
        admin2.setIdAmin(2);

        admins.add(admin1);
        admins.add(admin2);

        when(adminDaoMock.findAll()).thenReturn(admins);
        List<Admin> result = adminService.findAll();
        assertEquals(admins,result);
    }
    @Test
    void ShouldCreateAndReturnAdminGivenAdmin(){

        String username = "New Admin username";
        int id = 1;

        Admin expected = new Admin();
        expected.setIdAmin(id);
        expected.setUsername(username);

        when(adminDaoMock.create(any(Admin.class))).thenReturn(expected);

        Admin result = adminService.create(expected);
        assertEquals(expected, result);
    }
    @Test
    void ShouldFindByIdAdminGivenInt(){

        int id = 1;
        String username = "admin";
        Admin expected = new Admin();
        expected.setUsername(username);
        expected.setIdAmin(id);

        when(adminDaoMock.findById(any(int.class))).thenReturn(expected);

        Admin result = adminService.findById(id);
        assertEquals(expected, result);
    }
    @Test
    void ShouldReturnTrueGivenCategoryToUpdate(){

        int id = 1;
        Admin admin = new Admin();
        admin.setIdAmin(id);

        when(adminDaoMock.update(any(Admin.class))).thenReturn(true);

        boolean isModified = adminService.update(admin);
        assertEquals(true, isModified);
    }
    @Test
    void ShouldReturnTrueGivenAdminToDelete(){

        int id = 1;
        Admin admin = new Admin();
        admin.setIdAmin(id);

        when(adminDaoMock.delete(any(Admin.class))).thenReturn(true);

        boolean isDeleted = adminService.delete(admin);
        assertEquals(true, isDeleted);
    }
    @Test
    void ShouldReturnListOfAdminDto(){

        List<Admin> admins = new ArrayList<>();
        Admin admin1 = new Admin("admin1","password1");
        admin1.setIdAmin(1);
        Admin admin2 = new Admin("admin2","password2");
        admin2.setIdAmin(2);

        when(adminDaoMock.findAll()).thenReturn(admins);
        List<AdminDto> adminDtos = adminService.getAllAdmins();
        int i = 0;

        admins
                .stream()
                .forEach(admin -> {
                    assertEquals(admin.getIdAdmin(),adminDtos.get(i).getIdAdmin());
                    assertEquals(admin.getUsername(),adminDtos.get(i).getUsername());
                    assertEquals(admin.getIsSuperAdmin(),adminDtos.get(i).getIsSuperAdmin());
                    });
    }

}