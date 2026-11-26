package org.example.Questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;

public class ElementVisible implements Question<Boolean> {

    private final Target target;

    public ElementVisible(Target target) {
        this.target = target;
    }

    public static ElementVisible onScreen(Target target) {
        return new ElementVisible(target);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(target).answeredBy(actor);
    }
}