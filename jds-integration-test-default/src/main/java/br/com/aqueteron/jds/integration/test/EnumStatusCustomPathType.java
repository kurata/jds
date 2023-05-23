package br.com.aqueteron.jds.integration.test;

import br.com.aqueteron.jds.core.SimpleDomain;

@SimpleDomain(pathId = "custom-path-status")
public enum EnumStatusCustomPathType {

    OPENED, CLOSED, IN_PROGRESS

}
