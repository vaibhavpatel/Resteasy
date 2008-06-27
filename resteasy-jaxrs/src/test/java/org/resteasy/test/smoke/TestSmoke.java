package org.resteasy.test.smoke;

import org.junit.Assert;
import org.junit.Test;
import org.resteasy.Dispatcher;
import org.resteasy.mock.MockDispatcherFactory;
import org.resteasy.mock.MockHttpRequest;
import org.resteasy.mock.MockHttpResponse;
import org.resteasy.plugins.server.resourcefactory.POJOResourceFactory;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Simple smoke test
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class TestSmoke
{

   @Test
   public void testNoDefaultsResource() throws Exception
   {
      Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

      POJOResourceFactory noDefaults = new POJOResourceFactory(SimpleResource.class);
      dispatcher.getRegistry().addResourceFactory(noDefaults);

      {
         MockHttpRequest request = MockHttpRequest.get("/basic");
         MockHttpResponse response = new MockHttpResponse();

         dispatcher.invoke(request, response);


         Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
         Assert.assertEquals("basic", response.getContentAsString());
      }
      {
         MockHttpRequest request = MockHttpRequest.put("/basic");
         request.content("basic".getBytes());
         request.contentType("text/plain");
         MockHttpResponse response = new MockHttpResponse();

         dispatcher.invoke(request, response);


         Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
      }
      {
         MockHttpRequest request = MockHttpRequest.get("/queryParam?" + "param=" + URLEncoder.encode("hello world", "UTF-8"));
         MockHttpResponse response = new MockHttpResponse();

         dispatcher.invoke(request, response);


         Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
         Assert.assertEquals("hello world", response.getContentAsString());
      }
      {
         MockHttpRequest request = MockHttpRequest.get("/uriParam/1234");
         MockHttpResponse response = new MockHttpResponse();

         dispatcher.invoke(request, response);


         Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
         Assert.assertEquals("1234", response.getContentAsString());
      }
   }

   @Test
   public void testLocatingResource() throws Exception
   {
      Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

      POJOResourceFactory noDefaults = new POJOResourceFactory(LocatingResource.class);
      dispatcher.getRegistry().addResourceFactory(noDefaults);

      {
         MockHttpRequest request = MockHttpRequest.get("/locating/basic");
         MockHttpResponse response = new MockHttpResponse();

         dispatcher.invoke(request, response);


         Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
         Assert.assertEquals("basic", response.getContentAsString());
      }
      {
         MockHttpRequest request = MockHttpRequest.put("/locating/basic");
         request.content("basic".getBytes());
         request.contentType("text/plain");
         MockHttpResponse response = new MockHttpResponse();

         dispatcher.invoke(request, response);


         Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
      }
      {
         MockHttpRequest request = MockHttpRequest.get("/locating/queryParam?" + "param=" + URLEncoder.encode("hello world", "UTF-8"));
         MockHttpResponse response = new MockHttpResponse();

         dispatcher.invoke(request, response);


         Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
         Assert.assertEquals("hello world", response.getContentAsString());
      }
      {
         MockHttpRequest request = MockHttpRequest.get("/locating/uriParam/1234");
         MockHttpResponse response = new MockHttpResponse();

         dispatcher.invoke(request, response);


         Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
         Assert.assertEquals("1234", response.getContentAsString());
      }
   }
}
