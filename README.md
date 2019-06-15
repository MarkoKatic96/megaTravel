# megaTravel

![Docker services running...](services.png?raw=true "Docker services running...")

## Imena servisa i njihovi portovi:
- Administratorski backend mikroservis - admin-service [port:8000]
- Glavni agentski backend mikroservis - agent-global-service [port:8400]
- Lokalni agentski backend mikroservis - agent-local-service [port:8500]
- Eureka Discovery Server - discovery-service [port:8761]
- Korisnicki backend mikroservis - korisnik-service [port:8100]
- Glavni backend mikroservis za poruke - poruke-service [port:8700]
- Backend mikroservis za pretragu - pretraga-service [port:8200]
- Backend mikroservis za rejting - rating-service [port:8900]
- Backend mikroservis za rezervacije - reservation-service [port:8300]
- Backend mikroservis za smestaj - smestaj-service [port:8600]
- Zuul API gateway - zuul-gateway [port:8762]
