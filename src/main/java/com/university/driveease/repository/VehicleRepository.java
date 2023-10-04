package com.university.driveease.repository;

import com.university.driveease.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByUsername(String username);

    @Query(value = "SELECT v FROM Vehicle v WHERE v.vehicle_no = ?1")
    Optional<Vehicle> findByVehicle_no(String vehicle_no);
//    PreparedStatement preparedStatement = connection.prepareStatement("SELECT v FROM Vehicle v WHERE v.vehicle_no = ?");
//    preparedStatement.setString(1, userInput);
//    ResultSet resultSet = preparedStatement.executeQuery();

}
