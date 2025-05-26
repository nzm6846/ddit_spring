package kr.or.ddit.service.impl;

import kr.or.ddit.entity.Lprod;
import kr.or.ddit.repository.LprodRepository;
import kr.or.ddit.service.LprodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LprodServiceImpl implements LprodService {
    @Autowired
    LprodRepository lprodRepository;

    @Override
    public ArrayList<Lprod> findAll() {
        return this.lprodRepository.findAll();
    }
}
