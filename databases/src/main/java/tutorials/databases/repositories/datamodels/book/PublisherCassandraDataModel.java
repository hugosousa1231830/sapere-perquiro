//package tutorials.databases.repositories.datamodels.book;
//
//import org.springframework.data.cassandra.core.mapping.PrimaryKey;
//import org.springframework.data.cassandra.core.mapping.Table;
//import org.springframework.data.cassandra.core.mapping.Column;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//
//@Table("publishers")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class PublisherCassandraDataModel implements PublisherDataModel {
//
//    @PrimaryKey
//    private String id;
//
//    @Column("name")
//    private String name;
//
//    @Column("address")
//    private String address;
//}
