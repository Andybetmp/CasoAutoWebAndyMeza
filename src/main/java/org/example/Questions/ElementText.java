package org.example.Questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class ElementText implements Question<String> {

    private final Target target;

    public ElementText(Target target) {
        this.target = target;
    }

    public static ElementText of(Target target) {
        return new ElementText(target);
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(target).answeredBy(actor);
    }
}