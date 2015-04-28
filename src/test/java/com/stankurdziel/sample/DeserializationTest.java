package com.stankurdziel.sample;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;
import junit.framework.TestCase;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;

public class DeserializationTest extends TestCase
{
    public void testTypeCreation() {
        final Type expectedType1 = new TypeToken<Response<ResultT1>>() {}.getType();
        final Type expectedType2 = new TypeToken<Response<ResultT2>>() {}.getType();

        assertEquals(expectedType1, Response.responseType(ResultT1.class));
        assertEquals(expectedType2, Response.responseType(ResultT2.class));
    }

    public void testDeserializeUntypedResult() {
        String json = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"t1\":\"A string\"}}";

        Response<Map> rpc = Response.fromJson(json);
        assertJsonEquals(json, rpc);
        assertEquals("A string", rpc.getResult().get("t1"));
    }
    
    public void testDeserializeT1() throws Exception {
        String json = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"t1\":\"A string\"}}";

        Response<ResultT1> rpc = Response.fromJson(json, ResultT1.class);
        assertJsonEquals(json, rpc);
        assertEquals("A string", rpc.getResult().getT1());
    }

    public void testDeserializeT2() throws Exception {
        String json = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"t2\":5}}";

        Response<ResultT2> rpc = Response.fromJson(json, ResultT2.class);
        assertJsonEquals(json, rpc);
        assertEquals(5, rpc.getResult().getT2());
    }
}
