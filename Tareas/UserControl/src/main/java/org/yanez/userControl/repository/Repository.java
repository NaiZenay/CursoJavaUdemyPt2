package org.yanez.userControl.repository;

import java.util.List;

public interface Repository <T>{
     void register(T t);

     void delete(T t);

     List<T> listAll();

     void update(T t);

     T byID(Long id);

}
