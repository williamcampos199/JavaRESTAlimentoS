/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.AlimentosDao;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Alimento;

/**
 * REST Web Service
 *
 * @author William
 */
@Path("alimentos")
public class Alimentos {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Alimentos
     */
    public Alimentos() {
    }

    /**
     * Retrieves representation of an instance of ws.Alimentos
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public String getAlimentos() {
        AlimentosDao dao = new AlimentosDao();
        Gson gson = new Gson();
      return  gson.toJson(dao.Select());
        
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
      @Path("/{id}")
    public String getAlimentos(@PathParam("id") String id) {
        int idAlimento = Integer.parseInt(id);
        AlimentosDao dao = new AlimentosDao();
        Gson gson = new Gson();
      return  gson.toJson(dao.SelectByID(idAlimento));
        
    }
    
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
     @Path("/{id}")
    public void UpdateAlimento(@PathParam("id") String id  ,String content ) {
     if(id != null){
    Gson g = new Gson();
     Alimento alimento = (Alimento) g.fromJson(content, Alimento.class);
     alimento.setIdAlimento(Integer.parseInt(id));
    AlimentosDao dao = new AlimentosDao();
    dao.Update(alimento);
     }
     
    }
    
    @DELETE
    @Path("/{id}")
    public boolean excluir(@PathParam("id") String id){
        int idAlimento = Integer.parseInt(id);
       
        AlimentosDao dao = new AlimentosDao();
    return  dao.Delete(idAlimento);

    }
    
    
    @POST
   @Consumes(MediaType.APPLICATION_JSON)
     public void inserir(String content){
        Gson g = new Gson();
        Alimento alimento = g.fromJson(content, Alimento.class);
        AlimentosDao dao = new AlimentosDao();
        dao.Insert(alimento);
    }
    
        
    
    
}
