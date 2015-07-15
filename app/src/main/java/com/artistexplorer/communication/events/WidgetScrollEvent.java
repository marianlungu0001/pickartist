package com.artistexplorer.communication.events;

/**
 * Created by Marian Lungu on 14/07/15.
 */
public class WidgetScrollEvent {
    public enum Event{
        END, START, MIDDLE, SCROLL_UP, SCROLL_DOWN
    }
    Event event;

    public WidgetScrollEvent(Event event){
        this.event = event;

    }

    public Event getEvent(){
        return this.event;
    }
}
