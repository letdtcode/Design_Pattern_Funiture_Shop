package com.it.design_pattern_furniture_web.common.interfaces;

import java.util.ArrayList;

public interface IRetrieveEntity<ReturnType, EntityType, IdType> {
    ReturnType retrieveById(IdType entityId);
    ArrayList<ReturnType> retrieveAll(EntityType request);
}
