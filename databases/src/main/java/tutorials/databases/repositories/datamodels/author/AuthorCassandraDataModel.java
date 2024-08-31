//package tutorials.databases.repositories.datamodels.author;
//
//import org.springframework.data.cassandra.core.mapping.PrimaryKey;
//import org.springframework.data.cassandra.core.mapping.Table;
//import org.springframework.data.cassandra.core.mapping.Column;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//
//@Table("authors")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AuthorCassandraDataModel implements AuthorDataModel {
//
//    @PrimaryKey
//    private String id;
//    @Column("name")
//    private String name;
//    @Column("address")
//    private String address;
//}
