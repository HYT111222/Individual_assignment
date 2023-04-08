package com.example.ex3_2_back.initializer;

import com.example.ex3_2_back.entity.Movie;
import com.example.ex3_2_back.entity.User;
import com.example.ex3_2_back.entity.Shipment;
import com.example.ex3_2_back.repository.MovieRepository;
import com.example.ex3_2_back.repository.UserRepository;
import com.example.ex3_2_back.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 数据库初始化
 */
@Component
public class DataSourceInitializer implements CommandLineRunner {

    UserRepository userRepository;

    MovieRepository movieRepository;

    ShipmentRepository shipmentRepository;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setShipmentRepository(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    /**
     * Integer id;
     *     User fromUser;
     *     User toUser;
     *     String fromAddress;
     *     String toAddress;
     *     String parcelTrace;
     */
    void initializeShipments() {
        for (int i = 0; i < 50; i++) {
            var ship = Shipment.builder()
                    .id(i)
                    .fromUser("lisa")
                    .toUser("lisa")
                    .fromAddress(String.valueOf(i*4))
                    .toAddress(String.valueOf(i*4))
                    .toPhone("12344")
                    .fromPhone("189740")
                    .parcelTrace(String.valueOf(i*5))
                    .build();
            shipmentRepository.save(ship);
        }

//        shipmentRepository.save(Shipment.builder().name("shipment").build());
    }

    void initializeUsers() {
        for (int i = 0; i < 100; i++) {
            var user = User.builder()
                    .id(i)
                    .name(String.valueOf(i))
                    .password(String.valueOf(i))
                    .build();
            userRepository.save(user);
        }

        userRepository.save(User.builder().name("ZZQ").build());
    }

    void initializeMovies() {
        for (int i = 0; i < 100; i++) {
            var movie = Movie.builder()
                    .id(i)
                    .originalTitle(String.valueOf(i))
                    .voteAverage((float) (i * 100.0))
                    .build();
            movieRepository.save(movie);
        }
    }


    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
        initializeMovies();
        initializeShipments();
    }
}
