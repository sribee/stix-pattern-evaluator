/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.unstructured.stix.evaluator;

/**
 *
 * @author ccarv
 */
public class Qualifier extends BaseQualifer {

    private final String qualifer;

    private final BaseObservationExpression observationExpression;

    Qualifier(String qualifer, BaseObservationExpression observationExpression) {
        this.qualifer = qualifer;
        this.observationExpression = observationExpression;
    }

    /**
     * @return the qualifer
     */
    public String getQualifer() {
        return qualifer;
    }

    /**
     * @return the observationExpression
     */
    public BaseObservationExpression getObservationExpression() {
        return observationExpression;
    }

    @Override
    public String toString() {
        return this.getObservationExpression() + " Qualifier(" + this.getQualifer() + ")";
    }

}
