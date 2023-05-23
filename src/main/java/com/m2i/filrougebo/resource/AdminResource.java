package com.m2i.filrougebo.resource;
import com.m2i.filrougebo.dto.AdminDto;
import com.m2i.filrougebo.service.AdminService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;

@Path("/admin")
public class AdminResource {


    private AdminService adminService = new AdminService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAdmins() {
        List<AdminDto> admins = adminService.getAllAdmins();
        return Response.ok(admins).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminById(@PathParam("id") int id) {
        AdminDto admin = adminService.getAdminDtoById(id);
        if (admin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(admin).build();
    }
}

