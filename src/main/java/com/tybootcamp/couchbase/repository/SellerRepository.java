package com.tybootcamp.couchbase.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.tybootcamp.couchbase.domain.Seller;

import java.util.Optional;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends CouchbaseRepository<Seller, String> {

	@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
	void deleteByName(String name);

	@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
	Optional<Seller> findByName(String name);
}
