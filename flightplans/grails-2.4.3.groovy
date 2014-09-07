import java.nio.file.Paths

usingGrails('2.4.3')
testPlugin(artifactId: 'testable', version: '0.1', repositoryUrl: Paths.get(System.getProperty('user.home'), '.m2', 'repository').toString())
at(File.createTempDir().absolutePath)