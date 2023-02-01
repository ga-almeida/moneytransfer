CREATE TABLE clients (
	id              uuid NOT NULL,
	name            varchar NOT NULL,
	account_number  varchar NOT NULL,
	account_balance numeric NOT NULL,
	created_at      timestamp NOT NULL,
	updated_at      timestamp NULL,
	PRIMARY KEY (id)
);

CREATE TABLE transfers (
	id                  uuid NOT NULL,
	origin_client_id    uuid NULL,
	from_client_id      uuid NULL,
	"value"             numeric NOT NULL,
	status              varchar NOT NULL,
	created_at          timestamp NOT NULL,
	updated_at          timestamp NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (origin_client_id) REFERENCES clients(id),
	FOREIGN KEY (from_client_id) REFERENCES clients(id)
);