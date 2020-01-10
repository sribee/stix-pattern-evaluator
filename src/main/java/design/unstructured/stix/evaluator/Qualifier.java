/*
 * stix-pattern-evaluator
 * Copyright (C) 2020 - Christopher Carver
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
