package it.unibz.inf.ontop.docker.oracle;

import it.unibz.inf.ontop.docker.AbstractLeftJoinProfTest;

public class LeftJoinProfTestOracle extends AbstractLeftJoinProfTest {


    private static final String owlFileName = "/oracle/redundant_join/redundant_join_fk_test.owl";
    private static final String obdaFileName = "/oracle/redundant_join/redundant_join_fk_test.obda";
    private static final String propertyFileName = "/oracle/oracle.properties";


    public LeftJoinProfTestOracle() {
        super(owlFileName, obdaFileName, propertyFileName);
    }

}
