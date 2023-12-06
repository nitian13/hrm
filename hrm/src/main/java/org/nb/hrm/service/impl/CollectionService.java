package org.nb.hrm.service.impl;

import org.nb.hrm.entity.Collection;
import org.nb.hrm.mapper.CollectionMapper;
import org.nb.hrm.service.ICollectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CollectionService implements ICollectionService {

    private CollectionMapper collectionMapper;
    public CollectionService(CollectionMapper collectionMapper){
        this.collectionMapper=collectionMapper;
    }
    @Override
    public int add(Collection collection) {
        return collectionMapper.add(collection);
    }
}
