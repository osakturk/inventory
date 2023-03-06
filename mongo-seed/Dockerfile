FROM mongo

COPY inventory.json /inventory.json
COPY product.json /product.json

CMD mongoimport --host mongodb --db inventory --collection product --type json --file /product.json --jsonArray && \
mongoimport --host mongodb --db inventory --collection article --type json --file /inventory.json --jsonArray