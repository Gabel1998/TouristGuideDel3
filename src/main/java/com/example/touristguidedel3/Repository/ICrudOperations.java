package com.example.touristguidedel3.Repository;

import java.util.List;

public interface ICrudOperations<T> {
    List<T> findAll();
    T findById(int id);



}
