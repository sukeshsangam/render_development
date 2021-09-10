package org.janelia.render.service;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.janelia.alignment.ImageAndMask;
import org.janelia.alignment.RenderParameters;
import org.janelia.alignment.spec.ChannelSpec;
import org.janelia.alignment.spec.TileSpec;
import org.janelia.alignment.spec.TransformSpecMetaData;
import org.janelia.alignment.spec.stack.StackId;
import org.janelia.alignment.spec.stack.StackMetaData;
import org.janelia.render.service.dao.RenderDao;
import org.janelia.render.service.model.ObjectNotFoundException;
import org.janelia.render.service.model.RenderQueryParameters;
import org.janelia.render.service.util.RenderServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Path("/")
@Api(tags = {"Openseadragon API's"})
public class OpenseadragonService {

    private final RenderDao renderDao;
    private final RenderDataService renderDataService;

    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"})
    public OpenseadragonService()
            throws UnknownHostException {
        this(RenderDao.build());
    }

    private OpenseadragonService(final RenderDao renderDao) {
        this.renderDao = renderDao;
        this.renderDataService = new RenderDataService(renderDao);
    }


    @Path("v1/testservice")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Get test service")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "test service not found"),
    })
    public String getTestService(@FormParam("clustername") final String clustername,
                              @FormParam("username") final String username,
                              @FormParam("password") final String password,
                              @FormParam("StackOwner") final String stackowner,
                              @FormParam("StackProject") final String stackproject,
                              @FormParam("Stack") final String stack) {

        LOG.info("getTestService: entry, clustername={}, username={}, password={}",
                clustername, username, password);



        return clustername+" "+username+" "+password+" "+stackowner+" "+stackproject+" "+stack;
    }

    private static final Logger LOG = LoggerFactory.getLogger(OpenseadragonService.class);
}