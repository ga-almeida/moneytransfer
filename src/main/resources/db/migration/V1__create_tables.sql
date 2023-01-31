CREATE TABLE clients (
	id              uuid NOT NULL,
	name            varchar NOT NULL,
	accountNumber   varchar NOT NULL,
	accountBalance  numeric(16, 2) NOT NULL,
	createdAt       timestamp NOT NULL,
	updatedAt       timestamp NULL,
	PRIMARY KEY (id)
);

CREATE TABLE transfers (
	id                  uuid NOT NULL,
	origin_client_id    uuid NULL,
	from_client_id      uuid NULL,
	"value"             numeric(16, 2) NOT NULL,
	status              varchar NOT NULL,
	createdAt           timestamp NOT NULL,
	updatedAt           timestamp NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (origin_client_id) REFERENCES clients(id),
	FOREIGN KEY (from_client_id) REFERENCES clients(id)
);