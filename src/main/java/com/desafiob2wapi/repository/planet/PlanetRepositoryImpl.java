package com.desafiob2wapi.repository.planet;

public class PlanetRepositoryImpl {

	/*
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Planet> search(PlanetFilter planetFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Planet> criteria = builder.createQuery(Planet.class);		
		Root<Planet> root = criteria.from(Planet.class);
		Predicate[] predicate = where(planetFilter, builder, root);
		criteria.where(predicate);
		criteria.orderBy(builder.asc(root.get("name")));
		TypedQuery<Planet> query = entityManager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] where(PlanetFilter planetFilter, CriteriaBuilder builder, Root<Planet> root) {
		List<Predicate> predicates = new ArrayList<>();	
		if (!StringUtils.isEmpty(planetFilter.getName())) {
			predicates.add(builder.like(
				builder.lower(root.get("name")), planetFilter.getName().toLowerCase() + "%" ));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}*/
}
