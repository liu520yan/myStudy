package com.liuyan.study;

import lombok.extern.slf4j.Slf4j;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

/**
 * Created by liuyan on 2017/10/27.
 */
@Slf4j
public class AToBTests {
    // 1. Define State Machine Event
    enum FSMEvent {
        ToA, ToB, ToC, ToD
    }

    // 2. Define State Machine Class
    @StateMachineParameters(stateType = String.class, eventType = AToBTests.FSMEvent.class, contextType = Integer.class)
    static class StateMachineSample extends AbstractUntypedStateMachine {
        protected void fromAToB(String from, String to, AToBTests.FSMEvent event, Integer context) {
            System.out.println("Transition from '" + from + "' to '" + to + "' on event '" + event +
                    "' with context '" + context + "'.");
        }

        protected void ontoB(String from, String to, AToBTests.FSMEvent event, Integer context) {
            System.out.println("Entry State \'" + to + "\'.");
        }
    }

    public static void main(String[] args) {
        // 3. Build State Transitions
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(AToBTests.StateMachineSample.class);
        builder.externalTransition().from("A").to("B").on(AToBTests.FSMEvent.ToB).callMethod("fromAToB");
        builder.onEntry("B").callMethod("ontoB");

        // 4. Use State Machine
        UntypedStateMachine fsm = builder.newStateMachine("A");
        fsm.fire(AToBTests.FSMEvent.ToB, 10);

        System.out.println("Current state is " + fsm.getCurrentState());
    }
}
