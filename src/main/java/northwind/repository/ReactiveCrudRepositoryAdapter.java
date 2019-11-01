package northwind.repository;


import org.reactivestreams.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

public abstract class
   ReactiveCrudRepositoryAdapter<Entity, ID, Repo extends CrudRepository<Entity, ID>>
   implements ReactiveCrudRepository<Entity, ID> {

   protected  Repo repository;
   protected  Scheduler scheduler;

   @Override
   public <S extends Entity> Mono<S> save(S entity) {
      return Mono
         .fromCallable(() -> repository.save(entity))
         .subscribeOn(scheduler);
   }

   @Override
   public <S extends Entity> Flux<S> saveAll(Iterable<S> entities) {
      return Mono.fromCallable(() -> repository.saveAll(entities))
         .flatMapMany(Flux::fromIterable)
         .subscribeOn(scheduler);
   }

   @Override
   public <S extends Entity> Flux<S> saveAll(Publisher<S> entityStream) {
      return Flux.from(entityStream)
         .flatMap(entity -> Mono.fromCallable(() -> repository.save(entity)))
         .subscribeOn(scheduler);
   }

   @Override
   public Mono<Entity> findById(ID id) {
      return Mono.fromCallable(() -> repository.findById(id))
         .flatMap(result -> result
            .map(Mono::just)
            .orElseGet(Mono::empty))
         .subscribeOn(scheduler);
   }

   @Override
   public Mono<Entity> findById(Publisher<ID> id) {
      return Mono.from(id)
         .flatMap(actualId ->
            repository.findById(actualId)
               .map(Mono::just)
               .orElseGet(Mono::empty))
         .subscribeOn(scheduler);
   }

   @Override
   public Mono<Boolean> existsById(ID id) {
      return Mono
         .fromCallable(() -> repository.existsById(id))
         .subscribeOn(scheduler);
   }

   @Override
   public Mono<Boolean> existsById(Publisher<ID> id) {
      return Mono.from(id)
         .flatMap(actualId ->
            Mono.fromCallable(() -> repository.existsById(actualId)))
         .subscribeOn(scheduler);
   }

   @Override
   public Flux<Entity> findAll() {
      return Mono
         .fromCallable(repository::findAll)
         .flatMapMany(Flux::fromIterable)
         .subscribeOn(scheduler);
   }

   @Override
   public Flux<Entity> findAllById(Iterable<ID> ids) {
      return Mono
         .fromCallable(() -> repository.findAllById(ids))
         .flatMapMany(Flux::fromIterable)
         .subscribeOn(scheduler);
   }

   @Override
   public Flux<Entity> findAllById(Publisher<ID> idStream) {
      return Flux
         .from(idStream)
         .buffer()
         .flatMap(ids -> Flux.fromIterable(repository.findAllById(ids)))
         .subscribeOn(scheduler);
   }

   @Override
   public Mono<Long> count() {
      return Mono
         .fromCallable(repository::count)
         .subscribeOn(scheduler);
   }

   @Override
   public Mono<Void> deleteById(ID id) {
      return Mono
         .<Void>fromRunnable(() -> repository.deleteById(id))
         .subscribeOn(scheduler);
   }

   @Override
   public Mono<Void> deleteById(Publisher<ID> id) {
      return Mono.from(id)
         .flatMap(actualId ->
            Mono
               .<Void>fromRunnable(() -> repository.deleteById(actualId))
               .subscribeOn(scheduler)
         );
   }

   @Override
   public Mono<Void> delete(Entity entity) {
      return Mono
         .<Void>fromRunnable(() -> repository.delete(entity))
         .subscribeOn(scheduler);
   }

   @Override
   public Mono<Void> deleteAll(Iterable<? extends Entity> entities) {
      return Mono
         .<Void>fromRunnable(() -> repository.deleteAll(entities))
         .subscribeOn(scheduler);
   }

   @Override
   public Mono<Void> deleteAll(Publisher<? extends Entity> entityStream) {
      return Flux.from(entityStream)
         .flatMap(entity -> Mono
            .fromRunnable(() -> repository.delete(entity))
            .subscribeOn(scheduler))
         .then();
   }

   @Override
   public Mono<Void> deleteAll() {
      return Mono
         .<Void>fromRunnable(repository::deleteAll)
         .subscribeOn(scheduler);
   }
}

