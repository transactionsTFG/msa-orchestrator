package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import business.qualifier.CommitUserQualifier;
import business.qualifier.CreateAirlineReservationEventQualifier;
import business.qualifier.CreateRollbackAirlineReservationEventQualifier;
import business.qualifier.CreateRollbackHotelReservationEventQualifier;
import business.qualifier.GetTypeQualifierV2;
import business.qualifier.RemoveReservationAirlineRollbackQualifier;
import business.qualifier.RemoveReservationEventQualifier;
import business.qualifier.RemoveReservationHotelRollbackQualifier;
import business.qualifier.RollbackUserQualifier;
import business.qualifier.UpdateReservationAirlineRollbackQualifier;
import business.qualifier.UpdateReservationEventQualifier;
import business.qualifier.UpdateReservationHotelRollbackQualifier;
import business.user.ValidateUserQualifier;
import domainevent.command.handler.EventHandler;
import msa.commons.event.EventId;

@Singleton
@Startup
public class EventHandlerRegistry {
    private Map<EventId, EventHandler> handlers = new EnumMap<>(EventId.class);
    private EventHandler validateTypeUserHandler;
    private EventHandler commitUserEventHandler;
    private EventHandler rollbackUserEventHandler;
    private EventHandler validateUserHandler;
    private EventHandler creteReservationHandler; //Evento de Crear Reserva
    private EventHandler rollbackCreateReservationHotelHandler;
    private EventHandler rollbackCreateReservationAirlineHandler;

    private EventHandler removeReservationHandler;
    private EventHandler removeReservationAirlineRollbackHandler;
    private EventHandler removeReservationHotelRollbackHandler;

    private EventHandler updateReservationHandler;
    private EventHandler rollbackUpdateReservationAirlineHandler;
    private EventHandler rollbackUpdateReservationHotelHandler;

    @PostConstruct
    public void init() {
        this.handlers.put(EventId.VALIDATE_TYPE_USER, validateTypeUserHandler);
        this.handlers.put(EventId.CREATE_USER, commitUserEventHandler);
        this.handlers.put(EventId.FAILED_USER, rollbackUserEventHandler);
        this.handlers.put(EventId.VALIDATE_USER, validateUserHandler); //Evento de Validar Usuario

        this.handlers.put(EventId.CREATE_RESERVATION_TRAVEL, creteReservationHandler);
        this.handlers.put(EventId.ROLLBACK_CREATE_HOTEL_BOOKING, rollbackCreateReservationHotelHandler);
        this.handlers.put(EventId.RESERVATION_AIRLINE_CREATE_RESERVATION_ROLLBACK_SAGA, rollbackCreateReservationAirlineHandler);

        this.handlers.put(EventId.REMOVE_RESERVATION_TRAVEL, removeReservationHandler);
        this.handlers.put(EventId.RESERVATION_AIRLINE_REMOVE_RESERVATION_ROLLBACK_SAGA, removeReservationAirlineRollbackHandler);
        this.handlers.put(EventId.ROLLBACK_CREATE_HOTEL_BOOKING, removeReservationHotelRollbackHandler);

        this.handlers.put(EventId.UPDATE_RESERVATION_TRAVEL, updateReservationHandler);
        this.handlers.put(EventId.RESERVATION_AIRLINE_MODIFY_RESERVATION_ROLLBACK_SAGA, rollbackUpdateReservationAirlineHandler);
        this.handlers.put(EventId.ROLLBACK_UPDATE_HOTEL_BOOKING, rollbackUpdateReservationHotelHandler);
    }

    public EventHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

    @Inject
    public void setValidateTypeUserCommand(@GetTypeQualifierV2 EventHandler validateTypeUserHandler) {
        this.validateTypeUserHandler = validateTypeUserHandler;
    }

    @Inject
    public void setCommitUserEventHandler(@CommitUserQualifier EventHandler commitUserEventHandler) {
        this.commitUserEventHandler = commitUserEventHandler;
    }

    @Inject
    public void setRollbackUserEventHandler(@RollbackUserQualifier EventHandler rollbackUserEventHandler) {
        this.rollbackUserEventHandler = rollbackUserEventHandler;
    }

    @Inject
    public void setValidateUserHandler(@ValidateUserQualifier EventHandler validateUserCreateReservationAirlineHandler) {
        this.validateUserHandler = validateUserCreateReservationAirlineHandler;
    }


    @Inject
    public void setCreteReservationHandler(@CreateAirlineReservationEventQualifier EventHandler creteReservationHandler) {
        this.creteReservationHandler = creteReservationHandler;
    }

    @Inject
    public void setRollbackCreateReservationHotelHandler(@CreateRollbackHotelReservationEventQualifier EventHandler rollbackCreateReservationHotelHandler) {
        this.rollbackCreateReservationHotelHandler = rollbackCreateReservationHotelHandler;
    }

    @Inject
    public void setRollbackCreateReservationAirlineHandler(@CreateRollbackAirlineReservationEventQualifier EventHandler rollbackCreateReservationAirlineHandler) {
        this.rollbackCreateReservationAirlineHandler = rollbackCreateReservationAirlineHandler;
    }

    @Inject
    public void setRemoveReservationHandler(@RemoveReservationEventQualifier EventHandler removeReservationHandler) {
        this.removeReservationHandler = removeReservationHandler;
    }
    @Inject
    public void setRemoveReservationAirlineRollbackHandler(@RemoveReservationAirlineRollbackQualifier EventHandler removeReservationAirlineRollbackHandler) {
        this.removeReservationAirlineRollbackHandler = removeReservationAirlineRollbackHandler;
    }

    @Inject
    public void setRemoveReservationHotelRollbackHandler(@RemoveReservationHotelRollbackQualifier EventHandler removeReservationHotelRollbackHandler) {
        this.removeReservationHotelRollbackHandler = removeReservationHotelRollbackHandler;
    }
    
    @Inject
    public void setUpdateReservationHandler(@UpdateReservationEventQualifier EventHandler updateReservationHandler) {
        this.updateReservationHandler = updateReservationHandler;
    }

    @Inject
    public void setRollbackUpdateReservationAirlineHandler(@UpdateReservationAirlineRollbackQualifier EventHandler rollbackUpdateReservationAirlineHandler) {
        this.rollbackUpdateReservationAirlineHandler = rollbackUpdateReservationAirlineHandler;
    }

    @Inject
    public void setRollbackUpdateReservationHotelHandler(@UpdateReservationHotelRollbackQualifier EventHandler rollbackUpdateReservationHotelHandler) {
        this.rollbackUpdateReservationHotelHandler = rollbackUpdateReservationHotelHandler;
    }

}
    
