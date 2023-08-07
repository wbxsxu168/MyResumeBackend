package com.sunx.mysprmdbapp1.services;
import org.springframework.stereotype.Service;

/**
 * Sequence generator service interface.
 */

@Service
public interface SeqIDGenService {

    public String generateSequence(String seqName);

}