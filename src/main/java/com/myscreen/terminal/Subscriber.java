package com.myscreen.terminal;

import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.connection.ConnectionEventListener;

public class Subscriber {

  private Pusher pusher;

  public Subscriber() {
    pusher = new Pusher("02cdc5ce216d575e7d41");
  }

  public void subscribe() {
    pusher.connect(new ConnectionEventListener() {
      @Override
      public void onConnectionStateChange(ConnectionStateChange change) {
        System.out.println("State changed from " + change.getPreviousState() +
                           " to " + change.getCurrentState());
      }

      @Override
      public void onError(String message, String code, Exception e) {
        System.out.println("There was a problem connecting!");
      }
    }, ConnectionState.ALL);

    Channel channel = pusher.subscribe("test_channel");

    channel.bind("my_event", new SubscriptionEventListener() {
      @Override
      public void onEvent(String channelName, String eventName, final String data) {
        System.out.println(data);
      }
    });
  }

  public void unSubscribe() {
    pusher.disconnect();
  }
}
