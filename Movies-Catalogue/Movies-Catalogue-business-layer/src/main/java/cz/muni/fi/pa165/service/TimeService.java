/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;
/**
 *
 * @author Dominik
 */
@Service
public interface TimeService {
    
    public LocalDate getCurrentDate();
    
}
