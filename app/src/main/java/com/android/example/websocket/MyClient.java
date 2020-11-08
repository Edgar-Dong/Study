package com.android.example.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

/**
 * @author:無忌
 * @date:2020/8/25
 * @description:
 */
public class MyClient extends WebSocketClient {
    public MyClient(URI serverUri) {
        super(serverUri);
    }

    public MyClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("client:Hello, it is me. Mario :)");
        System.out.println("client:new connection opened");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("client:received message: " + message);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        System.out.println("client:received ByteBuffer");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("client:closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("client:an error occurred:" + ex);
    }

    public static void main(String[] args) throws URISyntaxException {
        WebSocketClient client = new MyClient(new URI("ws://localhost:8887"));
        client.connect();
        System.out.println("client:socket client run");
    }
}
