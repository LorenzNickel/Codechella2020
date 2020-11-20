package main.java.de.lorenznickel.ask.codechella;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class Utils {

    static boolean hasDisplay(HandlerInput input) {
        if (input.getRequestEnvelope().getContext() != null) {
            if (input.getRequestEnvelope().getContext().getSystem() != null) {
                if (input.getRequestEnvelope().getContext().getSystem().getDevice() != null) {
                    if (input.getRequestEnvelope().getContext().getSystem().getDevice().getSupportedInterfaces() != null) {
                        if (input.getRequestEnvelope().getContext().getSystem().getDevice().getSupportedInterfaces().getDisplay() != null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
