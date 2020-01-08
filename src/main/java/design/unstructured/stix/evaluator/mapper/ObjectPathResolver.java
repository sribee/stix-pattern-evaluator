/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.unstructured.stix.evaluator.mapper;

/**
 *
 * @author ccarv
 */
public interface ObjectPathResolver {

    Object getValue(Object object, String objectPath) throws StixMapperException;
}
