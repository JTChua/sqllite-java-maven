package com.tesdaciicc.data.dao;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

public interface DataAccessObject<T, Id, UUID> {

  List<T> getAll();

  Optional<T> getOne(ID id);

  T create(T entity);

  T update(T entity);

  void delete(ID id);

}